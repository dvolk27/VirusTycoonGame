package com.mygdx.game.Model;

public class Upgrade {
    private String upgradeName;
    private long basecost;
    private long upgradeValue;
    private int purchased;
    public Upgrade() {

    }
    public Upgrade(String upgradeName, long basecost, long upgradeValue, int purchased) {
        this.upgradeName = upgradeName;
        this.basecost = basecost;
        this.upgradeValue = upgradeValue;
        this.purchased = purchased;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public void setUpgradeName(String upgradeName) {
        this.upgradeName = upgradeName;
    }

    public long getBasecost() {
        return basecost;
    }

    public void setBasecost(long basecost) {
        this.basecost = basecost;
    }

    public long getUpgradeValue() {
        return upgradeValue;
    }

    public void setUpgradeValue(long upgradeValue) {
        this.upgradeValue = upgradeValue;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
