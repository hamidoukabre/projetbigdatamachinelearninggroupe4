📁 Structure du projet

Ce dépôt contient les différents éléments nécessaires au développement, à l'entraînement, et à la démonstration d'une application de prédiction du Close Price d'une action.

📂 app/
Application développée avec Streamlit pour la prédiction du Close Price.
Lancement de l'application :
    pip install -r requirements.txt
    streamlit run main.py

📂 bigdataml/
Contient les scripts du système mis en place pour récupérer les données depuis l'API Polygon.io.

📂 models/
Contient :
- Le script de création du modèle de prédiction
- Le modèle enregistré (gradient_boosting_model.pkl)

📂 RapportEtPrésentation/
Ce dossier contient :
- Le rapport technique détaillé
- Le fichier de présentation 

🎥 Démonstration
Une vidéo de démonstration de l'application est également incluse pour illustrer son fonctionnement en conditions réelles.
