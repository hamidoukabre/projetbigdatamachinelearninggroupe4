package unb.esi.bigdataml.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import unb.esi.bigdataml.model.Result;

@Repository
public interface ResultRepository extends ElasticsearchRepository<Result,String> {
}
