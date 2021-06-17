/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coop.home.bankaccount.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "ACCOUNTTRANSFERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounttransfers.findAll", query = "SELECT a FROM Accounttransfers a")})
public class Accounttransfers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccounttransfersPK accounttransfersPK;
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "transferdescription")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "symbol")
    private String symbol;
    
    @Basic(optional = false)
    @Column(name = "originalamount")
    private BigDecimal originalamount;
    
    @JoinColumns({
        @JoinColumn(name = "SOURCEACCOUNTIDCOMPANY", referencedColumnName = "USERSIDFINANCIALCOMPANY", insertable = false, updatable = false)
        , @JoinColumn(name = "SOURCEACCOUNTIDACCOUNT", referencedColumnName = "IDACCOUNT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Accounts accounts;
    @JoinColumns({
        @JoinColumn(name = "RECIPIENTACCOUNTIDCOMPANY", referencedColumnName = "USERSIDFINANCIALCOMPANY", insertable = false, updatable = false)
        , @JoinColumn(name = "RECIPIENTACCOUNTIDACCOUNT", referencedColumnName = "IDACCOUNT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Accounts accounts1;

    public Accounttransfers() {
    }

    public Accounttransfers(AccounttransfersPK accounttransfersPK) {
        this.accounttransfersPK = accounttransfersPK;
    }

    public Accounttransfers(AccounttransfersPK accounttransfersPK, BigDecimal amount, String description,String symbol,BigDecimal originalamount) {
        this.accounttransfersPK = accounttransfersPK;
        this.amount = amount;
        this.description = description;
        this.symbol = symbol;
        this.originalamount = originalamount;
    }

    public Accounttransfers(String transferdate, BigInteger recipientaccountidcompany, String recipientaccountidaccount, BigInteger sourceaccountidcompany, String sourceaccountidaccount) {
        this.accounttransfersPK = new AccounttransfersPK(transferdate, recipientaccountidcompany, recipientaccountidaccount, sourceaccountidcompany, sourceaccountidaccount);
    }

    public AccounttransfersPK getAccounttransfersPK() {
        return accounttransfersPK;
    }

    public void setAccounttransfersPK(AccounttransfersPK accounttransfersPK) {
        this.accounttransfersPK = accounttransfersPK;
    }

    public BigDecimal getOriginalamount() {
        return originalamount;
    }

    public void setOriginalamount(BigDecimal originalamount) {
        this.originalamount = originalamount;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Accounts getSourceAccount() {
        return accounts;
    }

    public void setSourceAccount(Accounts accounts) {
        this.accounts = accounts;
    }

    public Accounts getRecipentAccounts() {
        return accounts1;
    }

    public void setRecipentAccounts(Accounts accounts1) {
        this.accounts1 = accounts1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accounttransfersPK != null ? accounttransfersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounttransfers)) {
            return false;
        }
        Accounttransfers other = (Accounttransfers) object;
        if ((this.accounttransfersPK == null && other.accounttransfersPK != null) || (this.accounttransfersPK != null && !this.accounttransfersPK.equals(other.accounttransfersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Accounttransfers[ accounttransfersPK=" + accounttransfersPK + " ]";
    }
    
}
