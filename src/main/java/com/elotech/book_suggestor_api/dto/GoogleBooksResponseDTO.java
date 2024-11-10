package com.elotech.book_suggestor_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponseDTO {

    private List<GoogleBookItem> items;

    public List<GoogleBookItem> getItems() {
        return items;
    }

    public void setItems(List<GoogleBookItem> items) {
        this.items = items;
    }

    // Defina um Item para mapear os dados relevantes
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GoogleBookItem {
        private GoogleBookVolume volumeInfo;

        public GoogleBookVolume getVolumeInfo() {
            return volumeInfo;
        }

        public void setVolumeInfo(GoogleBookVolume volumeInfo) {
            this.volumeInfo = volumeInfo;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GoogleBookVolume {
        private String title;
        private List<String> authors;
        private List<IndustryIdentifier> industryIdentifiers;
        private String publishedDate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public void setAuthors(List<String> authors) {
            this.authors = authors;
        }

        public List<IndustryIdentifier> getIndustryIdentifiers() {
            return industryIdentifiers;
        }

        public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
            this.industryIdentifiers = industryIdentifiers;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        private String type;
        private String identifier;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }
}
