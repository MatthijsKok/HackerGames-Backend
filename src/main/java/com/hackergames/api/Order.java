package com.hackergames.api;


public final class Order
{
    private int id;
    private Status status;
    private String info;
    private long remainingTime;
    private long updateTime;


    public Order(final int id, final Status status, final String info, final long remainingTime, final long updateTime)
    {
        this.id = id;
        this.status = status;
        this.info = info;
        this.remainingTime = remainingTime;
        this.updateTime = updateTime;
    }


    public void update()
    {
        updateEta();
        updateStatus();
    }

    public void updateEta()
    {
        final long currentTime = System.currentTimeMillis();
        remainingTime -= (updateTime - currentTime);
        updateTime = currentTime;
    }

    public String updateStatus()
    {
        if (remainingTime < 15 * 60000)
        {
            return "on its way";
        }
        else if (remainingTime < 25 * 60000)
        {
            return "bakin'";
        }
        else
        {
            return "plz weet";
        }
    }


    public int getId()
    {
        return id;
    }

    public Status getStatus()
    {
        return status;
    }

    public String getInfo()
    {
        return info;
    }

    public long getRemainingTime()
    {
        return remainingTime;
    }

    public long getUpdateTime()
    {
        return updateTime;
    }


    public enum Status
    {
        Queued, Preparing, OnItsWay
    }
}
