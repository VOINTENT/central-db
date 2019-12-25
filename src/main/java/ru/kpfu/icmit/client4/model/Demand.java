package ru.kpfu.icmit.client4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name = "idGenerator", sequenceName = "demand_seq", allocationSize = 1)
public class Demand extends IdEntity {

    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_demand_nomenclature"))
    private Nomenclature nomenclature;
    private Integer count;

    @Override
    public String toString(){
        return nomenclature.toString() + " - "
                + "Количество: " + count.toString();
    }

    public String toString2(Organization organization){
       return "Спрос организацией \"" + organization.toString()
                + "\" в количестве " + count.toString();
    }
}
