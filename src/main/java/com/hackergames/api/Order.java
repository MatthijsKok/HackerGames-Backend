package com.hackergames.api;


public final class Order
{
    private int id;
    private String status;
    private String info;
    private long eta;
    private long updateDate;


    public Order(int id, String status, String info, long eta, long updateDate)
    {
        this.id = id;
        this.status = status;
        this.info = info;
        this.eta = eta;
        this.updateDate = updateDate;
    }


    public void updateEta()
    {
        final long currentTime = System.currentTimeMillis();
        eta -= (updateDate - currentTime);
        updateDate = currentTime;

        updateStatus();
    }

    public String updateStatus()
    {
        if (eta < 15 * 60000)
        {
            return "on its way";
        }
        else if (eta < 25 * 60000)
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

    public String getStatus()
    {
        return status;
    }

    public String getInfo()
    {
        return info;
    }

    public long getEta()
    {
        return eta;
    }

    public long getUpdateDate()
    {
        return updateDate;
    }
}
