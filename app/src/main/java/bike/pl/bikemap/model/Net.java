package bike.pl.bikemap.model;

/**
 * Created by Kacper on 2017-01-18.
 */
public class Net
{
    private Networks[] networks;

    public Networks[] getNetworks ()
    {
        return networks;
    }

    public void setNetworks (Networks[] networks)
    {
        this.networks = networks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [networks = "+networks+"]";
    }
}
