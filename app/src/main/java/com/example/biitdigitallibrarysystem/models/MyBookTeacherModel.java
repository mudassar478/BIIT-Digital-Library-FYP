package com.example.biitdigitallibrarysystem.models;


    public class MyBookTeacherModel {
        private String name;

        private String description;

        public MyBookTeacherModel(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }


