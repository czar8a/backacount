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
public class AccountsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDACOUNT")
    private String idacount;
    @Basic(optional = false)
    @Column(name = "USERSIDFINANCIALCOMPANY")
    private BigInteger usersidfinancialcompany;

    public AccountsPK() {
    }

    public AccountsPK(String idacount, BigInteger usersidfinancialcompany) {
        this.idacount = idacount;
        this.usersidfinancialcompany = usersidfinancialcompany;
    }

    public String getIdacount() {
        return idacount;
    }

    public void setIdacount(String idacount) {
        this.idacount = idacount;
    }

    public BigInteger getUsersidfinancialcompany() {
        return usersidfinancialcompany;
    }

    public void setUsersidfinancialcompany(BigInteger usersidfinancialcompany) {
        this.usersidfinancialcompany = usersidfinancialcompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacount != null ? idacount.hashCode() : 0);
        hash += (usersidfinancialcompany != null ? usersidfinancialcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountsPK)) {
            return false;
        }
        AccountsPK other = (AccountsPK) object;
        if ((this.idacount == null && other.idacount != null) || (this.idacount != null && !this.idacount.equals(other.idacount))) {
            return false;
        }
        if ((this.usersidfinancialcompany == null && other.usersidfinancialcompany != null) || (this.usersidfinancialcompany != null && !this.usersidfinancialcompany.equals(other.usersidfinancialcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AccountsPK[ idacount=" + idacount + ", usersidfinancialcompany=" + usersidfinancialcompany + " ]";
    }
    
}
