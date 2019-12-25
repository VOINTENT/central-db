package ru.kpfu.icmit.client4.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@NoArgsConstructor
@Data
@Builder
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name = "idGenerator", sequenceName = "metric_seq", allocationSize=1)
public class Metric extends IdEntity {
    private String code;
    private String metricName;

    public Metric(String code, String metricName) {
        this.code = code;
        this.metricName = metricName;
    }

    @Override
    public String toString(){
        return metricName;
    }
}
