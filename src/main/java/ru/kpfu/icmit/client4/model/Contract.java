package ru.kpfu.icmit.client4.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name = "idGenerator", sequenceName = "contract_seq", allocationSize = 1)
public class Contract extends IdEntity {
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_contract_demand"))
    private Demand demand;
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_contract_offer"))
    private Offer offer;
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_contract_nomenclature"))
    private Nomenclature nomenclature;
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_contract_organization_demanding"))
    private Organization demandingOrg;
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_contract_organization_offering"))
    private Organization offeringOrg;
    private Integer totalCount;
    private Double totalPrice;

    @Override
    public String toString(){
        return nomenclature.toString() + " - "
                + demand.toString2(demandingOrg) + " - "
                + offer.toString2(offeringOrg) + " - "
                + "Окончательная цена: " + totalPrice.toString() + " - "
                + "Окончательное количество: " + totalCount.toString();
    }
}
