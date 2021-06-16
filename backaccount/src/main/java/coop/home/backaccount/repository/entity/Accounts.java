/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.backaccount.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "ACCOUNTS")
@XmlRootElement
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountsPK accountsPK;
    @Basic(optional = false)
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
    private List<Accounttransfers> accounttransfersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts1")
    private List<Accounttransfers> accounttransfersList1;
    @JoinColumns({
        @JoinColumn(name = "LOGINUSER", referencedColumnName = "LOGINUSER", insertable = false, updatable = false)
        , @JoinColumn(name = "USERSIDFINANCIALCOMPANY", referencedColumnName = "IDFINANCIALCOMPANY", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Financialusers financialusers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
    private List<Transactions> transactionsList;

    public Accounts() {
    }

    public Accounts(AccountsPK accountsPK) {
        this.accountsPK = accountsPK;
    }

    public Accounts(AccountsPK accountsPK, BigDecimal balance) {
        this.accountsPK = accountsPK;
        this.balance = balance;
    }

    public Accounts(String idacount, BigInteger usersidfinancialcompany) {
        this.accountsPK = new AccountsPK(idacount, usersidfinancialcompany);
    }

    public AccountsPK getAccountsPK() {
        return accountsPK;
    }

    public void setAccountsPK(AccountsPK accountsPK) {
        this.accountsPK = accountsPK;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @XmlTransient
    public List<Accounttransfers> getAccounttransfersList() {
        return accounttransfersList;
    }

    public void setAccounttransfersList(List<Accounttransfers> accounttransfersList) {
        this.accounttransfersList = accounttransfersList;
    }

    @XmlTransient
    public List<Accounttransfers> getAccounttransfersList1() {
        return accounttransfersList1;
    }

    public void setAccounttransfersList1(List<Accounttransfers> accounttransfersList1) {
        this.accounttransfersList1 = accounttransfersList1;
    }

    public Financialusers getFinancialusers() {
        return financialusers;
    }

    public void setFinancialusers(Financialusers financialusers) {
        this.financialusers = financialusers;
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
        hash += (accountsPK != null ? accountsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.accountsPK == null && other.accountsPK != null) || (this.accountsPK != null && !this.accountsPK.equals(other.accountsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Accounts[ accountsPK=" + accountsPK + " ]";
    }
    
}
