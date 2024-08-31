package com.movies_hub.enricher;

import com.movies_hub.model.RequestMovies;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MongoEnricher {

    public Query getQueryByRequest(RequestMovies request) {
        Query query = new Query();

        if (StringUtils.isNotEmpty(request.getTitle())) {
            query.addCriteria(Criteria.where("title").is(request.getTitle()));
        }
        if (StringUtils.isNotEmpty(request.getActor())) {
            query.addCriteria(Criteria.where("cast").elemMatch(Criteria.where("firstName").is(request.getActor())));
        }
        if (CollectionUtils.isNotEmpty(request.getLanguage())) {
            query.addCriteria(Criteria.where("language").in(request.getLanguage()));
        }
        if (CollectionUtils.isNotEmpty(request.getGenre())) {
            query.addCriteria(Criteria.where("genre").in(request.getGenre()));
        }
        if (StringUtils.isNotEmpty(request.getReleaseDate())) {
            query.addCriteria(Criteria.where("releaseDate").is(request.getReleaseDate()));
        }

        log.info("Query from db by query: {}", query);
        return query;

    }

}
