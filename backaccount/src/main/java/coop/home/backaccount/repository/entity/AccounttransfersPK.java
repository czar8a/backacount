/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.repository.entity;

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
public class AccounttransfersPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TRANSFERDATE")
    private String transferdate;
    @Basic(optional = false)
    @Column(name = "RECIPIENTACCOUNTIDCOMPANY")
    private BigInteger recipientaccountidcompany;
    @Basic(optional = false)
    @Column(name = "RECIPIENTACCOUNTIDACOUNT")
    private String recipientaccountidacount;
    @Basic(optional = false)
    @Column(name = "SOURCEACCOUNTIDCOMPANY")
    private BigInteger sourceaccountidcompany;
    @Basic(optional = false)
    @Column(name = "SOURCEACCOUNTIDACOUNT")
    private String sourceaccountidacount;

    public AccounttransfersPK() {
    }

    public AccounttransfersPK(String transferdate, BigInteger recipientaccountidcompany, String recipientaccountidacount, BigInteger sourceaccountidcompany, String sourceaccountidacount) {
        this.transferdate = transferdate;
        this.recipientaccountidcompany = recipientaccountidcompany;
        this.recipientaccountidacount = recipientaccountidacount;
        this.sourceaccountidcompany = sourceaccountidcompany;
        this.sourceaccountidacount = sourceaccountidacount;
    }

    public String getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(String transferdate) {
        this.transferdate = transferdate;
    }

    public BigInteger getRecipientaccountidcompany() {
        return recipientaccountidcompany;
    }

    public void setRecipientaccountidcompany(BigInteger recipientaccountidcompany) {
        this.recipientaccountidcompany = recipientaccountidcompany;
    }

    public String getRecipientaccountidacount() {
        return recipientaccountidacount;
    }

    public void setRecipientaccountidacount(String recipientaccountidacount) {
        this.recipientaccountidacount = recipientaccountidacount;
    }

    public BigInteger getSourceaccountidcompany() {
        return sourceaccountidcompany;
    }

    public void setSourceaccountidcompany(BigInteger sourceaccountidcompany) {
        this.sourceaccountidcompany = sourceaccountidcompany;
    }

    public String getSourceaccountidacount() {
        return sourceaccountidacount;
    }

    public void setSourceaccountidacount(String sourceaccountidacount) {
        this.sourceaccountidacount = sourceaccountidacount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferdate != null ? transferdate.hashCode() : 0);
        hash += (recipientaccountidcompany != null ? recipientaccountidcompany.hashCode() : 0);
        hash += (recipientaccountidacount != null ? recipientaccountidacount.hashCode() : 0);
        hash += (sourceaccountidcompany != null ? sourceaccountidcompany.hashCode() : 0);
        hash += (sourceaccountidacount != null ? sourceaccountidacount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccounttransfersPK)) {
            return false;
        }
        AccounttransfersPK other = (AccounttransfersPK) object;
        if ((this.transferdate == null && other.transferdate != null) || (this.transferdate != null && !this.transferdate.equals(other.transferdate))) {
            return false;
        }
        if ((this.recipientaccountidcompany == null && other.recipientaccountidcompany != null) || (this.recipientaccountidcompany != null && !this.recipientaccountidcompany.equals(other.recipientaccountidcompany))) {
            return false;
        }
        if ((this.recipientaccountidacount == null && other.recipientaccountidacount != null) || (this.recipientaccountidacount != null && !this.recipientaccountidacount.equals(other.recipientaccountidacount))) {
            return false;
        }
        if ((this.sourceaccountidcompany == null && other.sourceaccountidcompany != null) || (this.sourceaccountidcompany != null && !this.sourceaccountidcompany.equals(other.sourceaccountidcompany))) {
            return false;
        }
        if ((this.sourceaccountidacount == null && other.sourceaccountidacount != null) || (this.sourceaccountidacount != null && !this.sourceaccountidacount.equals(other.sourceaccountidacount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AccounttransfersPK[ transferdate=" + transferdate + ", recipientaccountidcompany=" + recipientaccountidcompany + ", recipientaccountidacount=" + recipientaccountidacount + ", sourceaccountidcompany=" + sourceaccountidcompany + ", sourceaccountidacount=" + sourceaccountidacount + " ]";
    }
    
}
