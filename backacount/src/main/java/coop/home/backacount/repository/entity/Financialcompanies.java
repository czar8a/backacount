/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backacount.repository.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cochoa
 */
@Entity
@Table(name = "FINANCIALCOMPANIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Financialcompanies.findAll", query = "SELECT f FROM Financialcompanies f")})
public class Financialcompanies implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDFINANCIALCOMPANY")
    private BigInteger idfinancialcompany;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialcompanies")
    private List<Financialusers> financialusersList;

    public Financialcompanies() {
    }

    public Financialcompanies(BigInteger idfinancialcompany) {
        this.idfinancialcompany = idfinancialcompany;
    }

    public Financialcompanies(BigInteger idfinancialcompany, String name) {
        this.idfinancialcompany = idfinancialcompany;
        this.name = name;
    }

    public BigInteger getIdfinancialcompany() {
        return idfinancialcompany;
    }

    public void setIdfinancialcompany(BigInteger idfinancialcompany) {
        this.idfinancialcompany = idfinancialcompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Financialusers> getFinancialusersList() {
        return financialusersList;
    }

    public void setFinancialusersList(List<Financialusers> financialusersList) {
        this.financialusersList = financialusersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfinancialcompany != null ? idfinancialcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financialcompanies)) {
            return false;
        }
        Financialcompanies other = (Financialcompanies) object;
        if ((this.idfinancialcompany == null && other.idfinancialcompany != null) || (this.idfinancialcompany != null && !this.idfinancialcompany.equals(other.idfinancialcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Financialcompanies[ idfinancialcompany=" + idfinancialcompany + " ]";
    }
    
}
