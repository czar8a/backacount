/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backacount.repository.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cochoa
 */
@Embeddable
public class FinancialusersPK implements Serializable {

    
    @Basic(optional = false)
    @Column(name = "IDFINANCIALCOMPANY")
    private BigInteger idfinancialcompany;
    @Basic(optional = false)
    @Column(name = "LOGINUSER")
    private String loginuser;

    public FinancialusersPK() {
    }

    public FinancialusersPK(String loginuser, BigInteger idfinancialcompany) {
        this.loginuser = loginuser;
        this.idfinancialcompany = idfinancialcompany;
    }

    public String getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(String loginuser) {
        this.loginuser = loginuser;
    }

    public BigInteger getIdfinancialcompany() {
        return idfinancialcompany;
    }

    public void setIdfinancialcompany(BigInteger idfinancialcompany) {
        this.idfinancialcompany = idfinancialcompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginuser != null ? loginuser.hashCode() : 0);
        hash += (idfinancialcompany != null ? idfinancialcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinancialusersPK)) {
            return false;
        }
        FinancialusersPK other = (FinancialusersPK) object;
        if ((this.loginuser == null && other.loginuser != null) || (this.loginuser != null && !this.loginuser.equals(other.loginuser))) {
            return false;
        }
        if ((this.idfinancialcompany == null && other.idfinancialcompany != null) || (this.idfinancialcompany != null && !this.idfinancialcompany.equals(other.idfinancialcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FinancialusersPK[ loginuser=" + loginuser + ", idfinancialcompany=" + idfinancialcompany + " ]";
    }
    
}
