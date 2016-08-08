/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.util;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Artifact_;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Virginie
 */
public class PredicateHelper {

    public static List<Predicate> generateArtifactTitleLikePredicates(String string, CriteriaBuilder criteriaBuilder, Root<Artifact> artifactRoot) {
        System.out.println("generateArtifactTitleLikePredicates " + string);
        List<Predicate> predicates = new ArrayList<>();
        if (string != null) {

            String[] words = string.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            if (words.length > 0) {
                List<Predicate> orTitleLikes = new ArrayList<>();
                for (String word : words) {
                    Predicate titleLike = criteriaBuilder.like(
                            criteriaBuilder.lower(artifactRoot.get(Artifact_.title)),
                            "%" + word + "%");
                    orTitleLikes.add(titleLike);
                }
                Predicate or = criteriaBuilder.or(orTitleLikes.toArray(new Predicate[orTitleLikes.size()]));
                predicates.add(or);
            }
        }
        return predicates;
    }
}
