package ru.kpfu.icmit.client4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "organization_seq", allocationSize=1)
public class Organization extends IdEntity {
    private String name;

    @Override
    public String toString(){
        return name;
    }
}
