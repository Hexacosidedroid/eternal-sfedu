package ru.cib.eternalsfedu.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "events")
 public class Events extends Domain{

    Date eventDate;
    String eventTitle;
    String eventDescription;
    String eventImages;

    Date creationTime;
    long heatCounter;
    Date heatExpirationTime;
}
