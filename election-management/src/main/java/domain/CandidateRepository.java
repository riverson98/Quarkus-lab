package domain;

import java.util.List;

//abstração do banco de dados.
public interface CandidateRepository{
    void save(List<Candidate> candidates);
    default void save(Candidate candidate){
        save(List.of(candidate));
    }

    List<Candidate> findAllCandidates();

}
