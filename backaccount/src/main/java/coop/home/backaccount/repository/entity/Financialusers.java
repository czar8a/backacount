/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.repository.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cochoa
 */
@Entity
@Table(name = "FINANCIALUSERS")
@XmlRootElement
public class Financialusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FinancialusersPK financialusersPK;
    
    @Basic(optional = false)
    @Column(name = "IDDOCUMENT")
    private String iddocument;
    @Basic(optional = false)
    @Column(name = "IDDOCTYPE")
    private String iddoctype;
    
    @Basic(optional = false)
    @Column(name = "PASS")
    private String pass;
    
    @Basic(optional = false)
    @Column(name = "financialusername")
    private String financialusername;
    
    @Basic(optional = false)
    @Column(name = "financialuserlastname")
    private String financialuserlastname;
    
    @JoinColumn(name = "IDDOCTYPE", referencedColumnName = "IDDOCTYPE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documenttypes documenttypes;
    @JoinColumn(name = "IDFINANCIALCOMPANY", referencedColumnName = "IDFINANCIALCOMPANY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Financialcompanies financialcompanies;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialusers")
    private List<Accounts> accountsList;

    public Financialusers() {
    }

    public Financialusers(FinancialusersPK financialusersPK) {
        this.financialusersPK = financialusersPK;
    }

    public Financialusers(FinancialusersPK financialusersPK,String iddocument, String iddoctype,  
    		String pass,String financialusername,String financialuserlastname) {
        this.financialusersPK = financialusersPK;
        this.iddocument = iddocument;
        this.iddoctype = iddoctype;
        this.pass = pass;
        this.financialusername = financialusername;
        this.financialuserlastname = financialuserlastname;
    }

    public Financialusers(String loginuser, BigInteger idfinancialcompany) {
        this.financialusersPK = new FinancialusersPK(loginuser, idfinancialcompany);
    }

    public FinancialusersPK getFinancialusersPK() {
        return financialusersPK;
    }

    public void setFinancialusersPK(FinancialusersPK financialusersPK) {
        this.financialusersPK = financialusersPK;
    }
    
    public String getIddocument() {
        return iddocument;
    }

    public void setIddocument(String iddocument) {
        this.iddocument = iddocument;
    }

    public String getIddoctype() {
        return iddoctype;
    }

    public void setIddoctype(String iddoctype) {
        this.iddoctype = iddoctype;
    }

    

    public String getFinancialusername() {
        return financialusername;
    }

    public void setFinancialusername(String financialusername) {
        this.financialusername = financialusername;
    }
    
    public String getFinancialuserlastname() {
        return financialuserlastname;
    }

    public void setFinancialuserlastname(String financialuserlastname) {
        this.financialuserlastname = pass;
    }
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Documenttypes getDocumenttypes() {
        return documenttypes;
    }

    public void setDocumenttypes(Documenttypes documenttypes) {
        this.documenttypes = documenttypes;
    }

    public Financialcompanies getFinancialcompanies() {
        return financialcompanies;
    }

    public void setFinancialcompanies(Financialcompanies financialcompanies) {
        this.financialcompanies = financialcompanies;
    }

    @XmlTransient
    public List<Accounts> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Accounts> accountsList) {
        this.accountsList = accountsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (financialusersPK != null ? financialusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financialusers)) {
            return false;
        }
        Financialusers other = (Financialusers) object;
        if ((this.financialusersPK == null && other.financialusersPK != null) || (this.financialusersPK != null && !this.financialusersPK.equals(other.financialusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Financialusers[ financialusersPK=" + financialusersPK + " ]";
    }
    
}
