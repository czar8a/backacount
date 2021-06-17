/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.bankaccount.repository.entity;

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
    @Column(name = "RECIPIENTACCOUNTIDACCOUNT")
    private String recipientaccountidaccount;
    @Basic(optional = false)
    @Column(name = "SOURCEACCOUNTIDCOMPANY")
    private BigInteger sourceaccountidcompany;
    @Basic(optional = false)
    @Column(name = "SOURCEACCOUNTIDACCOUNT")
    private String sourceaccountidaccount;

    public AccounttransfersPK() {
    }

    public AccounttransfersPK(String transferdate, BigInteger recipientaccountidcompany, String recipientaccountidaccount, BigInteger sourceaccountidcompany, String sourceaccountidaccount) {
        this.transferdate = transferdate;
        this.recipientaccountidcompany = recipientaccountidcompany;
        this.recipientaccountidaccount = recipientaccountidaccount;
        this.sourceaccountidcompany = sourceaccountidcompany;
        this.sourceaccountidaccount = sourceaccountidaccount;
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

    public String getRecipientaccountidaccount() {
        return recipientaccountidaccount;
    }

    public void setRecipientaccountidaccount(String recipientaccountidaccount) {
        this.recipientaccountidaccount = recipientaccountidaccount;
    }

    public BigInteger getSourceaccountidcompany() {
        return sourceaccountidcompany;
    }

    public void setSourceaccountidcompany(BigInteger sourceaccountidcompany) {
        this.sourceaccountidcompany = sourceaccountidcompany;
    }

    public String getSourceaccountidaccount() {
        return sourceaccountidaccount;
    }

    public void setSourceaccountidaccount(String sourceaccountidaccount) {
        this.sourceaccountidaccount = sourceaccountidaccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transferdate != null ? transferdate.hashCode() : 0);
        hash += (recipientaccountidcompany != null ? recipientaccountidcompany.hashCode() : 0);
        hash += (recipientaccountidaccount != null ? recipientaccountidaccount.hashCode() : 0);
        hash += (sourceaccountidcompany != null ? sourceaccountidcompany.hashCode() : 0);
        hash += (sourceaccountidaccount != null ? sourceaccountidaccount.hashCode() : 0);
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
        if ((this.recipientaccountidaccount == null && other.recipientaccountidaccount != null) || (this.recipientaccountidaccount != null && !this.recipientaccountidaccount.equals(other.recipientaccountidaccount))) {
            return false;
        }
        if ((this.sourceaccountidcompany == null && other.sourceaccountidcompany != null) || (this.sourceaccountidcompany != null && !this.sourceaccountidcompany.equals(other.sourceaccountidcompany))) {
            return false;
        }
        if ((this.sourceaccountidaccount == null && other.sourceaccountidaccount != null) || (this.sourceaccountidaccount != null && !this.sourceaccountidaccount.equals(other.sourceaccountidaccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AccounttransfersPK[ transferdate=" + transferdate + ", recipientaccountidcompany=" + recipientaccountidcompany + ", recipientaccountidaccount=" + recipientaccountidaccount + ", sourceaccountidcompany=" + sourceaccountidcompany + ", sourceaccountidaccount=" + sourceaccountidaccount + " ]";
    }
    
}
