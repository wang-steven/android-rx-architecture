package tw.steven.project.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class SearchEngineResults {

    public List<Topic> RelatedTopics;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Topic {

        public String firstURL;
        public String text;
        public List<Topic> topics;

    }
}
