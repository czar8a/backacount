/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "TRANSACTIONTYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactiontypes.findAll", query = "SELECT t FROM Transactiontypes t")})
public class Transactiontypes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDTRANSACTIONTYPE")
    private BigDecimal idtransactiontype;
    @Basic(optional = false)
    @Column(name = "TRANSACTIONNAME")
    private String transactionname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactiontypes")
    private List<Transactions> transactionsList;

    public Transactiontypes() {
    }

    public Transactiontypes(BigDecimal idtransactiontype) {
        this.idtransactiontype = idtransactiontype;
    }

    public Transactiontypes(BigDecimal idtransactiontype, String transactionname) {
        this.idtransactiontype = idtransactiontype;
        this.transactionname = transactionname;
    }

    public BigDecimal getIdtransactiontype() {
        return idtransactiontype;
    }

    public void setIdtransactiontype(BigDecimal idtransactiontype) {
        this.idtransactiontype = idtransactiontype;
    }

    public String getTransactionname() {
        return transactionname;
    }

    public void setTransactionname(String transactionname) {
        this.transactionname = transactionname;
    }

    @XmlTransient
    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransactiontype != null ? idtransactiontype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactiontypes)) {
            return false;
        }
        Transactiontypes other = (Transactiontypes) object;
        if ((this.idtransactiontype == null && other.idtransactiontype != null) || (this.idtransactiontype != null && !this.idtransactiontype.equals(other.idtransactiontype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Transactiontypes[ idtransactiontype=" + idtransactiontype + " ]";
    }
    
}
