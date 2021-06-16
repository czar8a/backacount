/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.repository.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cochoa
 */
@Entity
@Table(name = "DOCUMENTTYPES")
@XmlRootElement
public class Documenttypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDDOCTYPE")
    private String iddoctype;
    @Basic(optional = false)
    @Column(name = "TYPENAME")
    private String typename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documenttypes")
    private List<Financialusers> financialusersList;

    public Documenttypes() {
    }

    public Documenttypes(String iddoctype) {
        this.iddoctype = iddoctype;
    }

    public Documenttypes(String iddoctype, String typename) {
        this.iddoctype = iddoctype;
        this.typename = typename;
    }

    public String getIddoctype() {
        return iddoctype;
    }

    public void setIddoctype(String iddoctype) {
        this.iddoctype = iddoctype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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
        hash += (iddoctype != null ? iddoctype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documenttypes)) {
            return false;
        }
        Documenttypes other = (Documenttypes) object;
        if ((this.iddoctype == null && other.iddoctype != null) || (this.iddoctype != null && !this.iddoctype.equals(other.iddoctype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documenttypes[ iddoctype=" + iddoctype + " ]";
    }
    
}
