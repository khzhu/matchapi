package com.pughlab.matchapi.web;

import com.pughlab.matchapi.domain.TrialMatch;
import com.pughlab.matchapi.repository.TrialMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST web services for retrieving matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */

@RestController
@RequestMapping("/api")
public class TrialMatchController {

    private TrialMatchRepository repository;

    @Autowired
    public void setRepository(TrialMatchRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createBook(@RequestBody TrialMatch trialMatch) {
        repository.save(trialMatch);
    }

    @RequestMapping(method = RequestMethod.GET, value="/matches")
    public List<TrialMatch> findAllTrialMatches() {
        return repository.findAll();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/id/{id}")
    public TrialMatch findTrialMatchById(@PathVariable String id) {
        return repository.findById(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/gene/{gene}")
    public List<TrialMatch> findBookByHugoSymbol(@PathVariable String gene) {

        return repository.findTrialMatchByHugoSymbol(gene);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/matches/id/{id}")
    public void deleteBookWithId(@PathVariable TrialMatch trialMatch) {
        repository.delete(trialMatch);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBooks() {
        repository.deleteAll();
    }
}