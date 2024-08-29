package com.example.testtaskcniitu.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @JacksonXmlProperty(localName = "personalinfo")
    private PersonalInfo personalInfo;

    @JacksonXmlProperty(localName = "contactinfo")
    private ContactInfo contactInfo;

    @JacksonXmlProperty(localName = "employment")
    private Employment employment;

    @JacksonXmlProperty(localName = "education")
    private Education education;

    @JacksonXmlElementWrapper(localName = "skills")
    @JacksonXmlProperty(localName = "skill")
    private List<String> skills;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Employment getEmployment() {
        return employment;
    }

    public Education getEducation() {
        return education;
    }

    public List<String> getSkills() {
        return skills;
    }

    public static class PersonalInfo {
        @JacksonXmlProperty(localName = "firstname")
        private String firstName;

        @JacksonXmlProperty(localName = "lastname")
        private String lastName;

        @JacksonXmlProperty(localName = "email")
        private String email;

        @JacksonXmlProperty(localName = "dateofbirth")
        private String dateOfBirth;

        @JacksonXmlProperty(localName = "gender")
        private String gender;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getGender() {
            return gender;
        }
    }

    public static class ContactInfo {
        @JacksonXmlProperty(localName = "phonenumber")
        private String phoneNumber;

        @JacksonXmlProperty(localName = "address")
        private Address address;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Address getAddress() {
            return address;
        }

        public static class Address {
            @JacksonXmlProperty(localName = "street")
            private String street;

            @JacksonXmlProperty(localName = "city")
            private String city;

            @JacksonXmlProperty(localName = "state")
            private String state;

            @JacksonXmlProperty(localName = "postalcode")
            private String postalCode;

            @JacksonXmlProperty(localName = "country")
            private String country;

            public String getStreet() {
                return street;
            }

            public String getCity() {
                return city;
            }

            public String getState() {
                return state;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public String getCountry() {
                return country;
            }
        }
    }

    public static class Employment {
        @JacksonXmlProperty(localName = "companyname")
        private String companyName;

        @JacksonXmlProperty(localName = "position")
        private String position;

        @JacksonXmlProperty(localName = "startdate")
        private String startDate;

        @JacksonXmlProperty(localName = "enddate")
        private String endDate;

        public String getCompanyName() {
            return companyName;
        }

        public String getPosition() {
            return position;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }
    }

    public static class Education {
        @JacksonXmlProperty(localName = "universityname")
        private String universityName;

        @JacksonXmlProperty(localName = "degree")
        private String degree;

        @JacksonXmlProperty(localName = "graduationyear")
        private String graduationYear;

        public String getUniversityName() {
            return universityName;
        }

        public String getDegree() {
            return degree;
        }

        public String getGraduationYear() {
            return graduationYear;
        }
    }
}
