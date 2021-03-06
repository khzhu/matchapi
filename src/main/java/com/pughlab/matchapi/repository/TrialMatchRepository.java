package com.pughlab.matchapi.repository;


import com.pughlab.matchapi.domain.TrialMatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

/**
 * Interface for REST APIs for retrieving matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */

public interface TrialMatchRepository extends MongoRepository<TrialMatch, Integer> {

    TrialMatch findById(String id);

    List<TrialMatch> findTrialMatchByHugoSymbol(String hugoSymbol);

    //Supports native JSON query string
    @Query("{sample_id:'?0'}")
    List<TrialMatch> findTrialMatchBySampleID(String sample_id);

    @Query("{sample_id: { $regex: ?0 } })")
    List<TrialMatch> findCustomByRegExSSampleID(String sampleID);

    @Override
    void delete(TrialMatch match);
}
