import streamlit as st
import pandas as pd
import numpy as np
import joblib
from PIL import Image

# Charger les images de bannière
top_image = Image.open('static/banner_top.png')
bottom_image = Image.open('static/banner_bottom.png')
main_image = Image.open('static/main_banner.png')

# Afficher l’image de bannière dans le sidebar
st.sidebar.image(top_image, use_column_width='auto')

# Sélection de mode d’entrée
input_mode = st.sidebar.radio("Choisissez la méthode d'entrée :", ["Charger un fichier CSV", "Entrer manuellement les valeurs"])

# Afficher l'image principale
st.image(main_image, use_column_width='auto')

# Titre de l'application
st.title("🔮 Prédiction du prix de clôture (ClosePrice)")

# Charger le modèle
model = joblib.load("gradient_boosting_model.pkl")

# Fonction pour faire des prédictions (avec transformation inverse log1p → expm1)
def predict(df):
    # Réorganiser les colonnes dans l'ordre du modèle
    expected_order = ["volume", "highPrice", "lowPrice", "openPrice"]
    df = df[expected_order]
    prediction_log = model.predict(df)
    prediction = np.expm1(prediction_log)  # transformation inverse du log
    return prediction

if input_mode == "Charger un fichier CSV":
    uploaded_file = st.file_uploader("Chargez un fichier CSV contenant les colonnes : volume, openPrice, highPrice, lowPrice", type=["csv"])
    
    if uploaded_file is not None:
        try:
            df = pd.read_csv(uploaded_file)
            required_cols = ["volume", "openPrice", "highPrice", "lowPrice"]
            if not all(col in df.columns for col in required_cols):
                st.error("❌ Le fichier doit contenir les colonnes : volume, openPrice, highPrice, lowPrice")
            else:
                st.write("Aperçu du fichier chargé :")
                st.dataframe(df)

                if st.button("Faire la prédiction"):
                    try:
                        prediction = predict(df)
                        df["Predicted_ClosePrice"] = prediction
                        st.success("✅ Prédictions terminées.")
                        st.dataframe(df)

                        # Téléchargement
                        csv = df.to_csv(index=False).encode('utf-8')
                        st.download_button("📥 Télécharger les résultats", csv, "predictions.csv", "text/csv")
                    except Exception as e:
                        st.error(f"Erreur lors de la prédiction : {e}")
        except Exception as e:
            st.error(f"Erreur de lecture du fichier : {e}")

else:
    st.markdown("### 📝 Entrer les valeurs manuellement")

    volume = st.number_input("Volume", min_value=0.0)
    open_price = st.number_input("Open Price", min_value=0.0)
    high_price = st.number_input("High Price", min_value=0.0)
    low_price = st.number_input("Low Price", min_value=0.0)

    if st.button("Faire la prédiction"):
        input_df = pd.DataFrame([{
            "volume": volume,
            "openPrice": open_price,
            "highPrice": high_price,
            "lowPrice": low_price
        }])
        try:
            # Réordonner les colonnes comme attendu par le modèle
            input_df = input_df[["volume", "highPrice", "lowPrice", "openPrice"]]
            prediction = predict(input_df)
            st.success(f"📈 Le prix de clôture prédit est : **{prediction[0]:.2f}**")
        except Exception as e:
            st.error(f"Erreur lors de la prédiction : {e}")

# Image en bas du sidebar
st.sidebar.image(bottom_image, use_column_width='auto')

# Styles supplémentaires
st.markdown("""
    <style>
    #MainMenu {visibility: hidden;}
    footer {visibility: hidden;}
    </style>
    """, unsafe_allow_html=True)

# Message de signature
st.markdown("<br><hr><center>Made with ❤️ by KABRE Hamidou<br>LOMPO Abel<br>TRAORE Oumar<br>KIENTAGA Laurent</center><hr>", unsafe_allow_html=True)
