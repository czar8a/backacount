/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.bankaccount.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cochoa
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransactionsPK transactionsPK;
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @JoinColumns({
        @JoinColumn(name = "ACCOUNTIDFINANCIALCOMPANY", referencedColumnName = "USERSIDFINANCIALCOMPANY", insertable = false, updatable = false)
        , @JoinColumn(name = "IDACCOUNT", referencedColumnName = "IDACCOUNT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Accounts accounts;
    @JoinColumn(name = "IDTIPOTRANSACCION", referencedColumnName = "IDTRANSACTIONTYPE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transactiontypes transactiontypes;

    public Transactions() {
    }

    public Transactions(TransactionsPK transactionsPK) {
        this.transactionsPK = transactionsPK;
    }

    public Transactions(TransactionsPK transactionsPK, BigDecimal amount) {
        this.transactionsPK = transactionsPK;
        this.amount = amount;
    }

    public Transactions(BigInteger accountidfinancialcompany, String idaccount, BigInteger idtipotransaccion, String transactiondate) {
        this.transactionsPK = new TransactionsPK(accountidfinancialcompany, idaccount, idtipotransaccion, transactiondate);
    }

    public TransactionsPK getTransactionsPK() {
        return transactionsPK;
    }

    public void setTransactionsPK(TransactionsPK transactionsPK) {
        this.transactionsPK = transactionsPK;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Transactiontypes getTransactiontypes() {
        return transactiontypes;
    }

    public void setTransactiontypes(Transactiontypes transactiontypes) {
        this.transactiontypes = transactiontypes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionsPK != null ? transactionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionsPK == null && other.transactionsPK != null) || (this.transactionsPK != null && !this.transactionsPK.equals(other.transactionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Transactions[ transactionsPK=" + transactionsPK + " ]";
    }
    
}
