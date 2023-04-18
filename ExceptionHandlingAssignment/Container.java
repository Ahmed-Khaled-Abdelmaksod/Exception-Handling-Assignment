public class Container implements Comparable<Container>{
    private String id;
    private String sn;
    private String ln;
    public Container(String id,String sn,String ln)
    {
        this.id =id;
        this.sn = sn;
        this.ln = ln;
    }

    public String getId() {
        return id;
    }

    public String getSn() {
        return sn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getLn() {
        return ln;
    }
    public String toString()
    {
        return 	"\t<CONTAINER UU"+this.getId()+">\n"
                +"\t\t<SHORT-NAME>"+getSn()+"</SHORT-NAME>\n"
                +"\t\t<LONG-NAME>"+getLn()+"</LONG-NAME>\n"
                +"\t</CONTAINER>\n";
    }
    @Override
    public int compareTo(Container c)
    {
        if((this.getSn().charAt(getSn().length()-1)) > (c.getSn().charAt(getSn().length()-1)))
        {
            return 1;
        }else if ((this.getSn().charAt(getSn().length()-1)) < (c.getSn().charAt(getSn().length()-1)))
        {
            return -1;
        }
        else {
            return 0;
        }
    }
}
