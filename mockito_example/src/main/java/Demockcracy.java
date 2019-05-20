public class Demockcracy {

    private President president;

    Demockcracy(President president){
        this.president = president;
    }

    //Visit from other states
    public void stateVisit(){
        president.greet();
    }

    //After a scandal the president has to resign
    public String scandal(){
        String reason = president.resign();
        president = null;
        return reason;
    }

    //Pass a Bill
    public boolean passBill(String bill){
        boolean passed = false;
        try {
            president.signBill(bill);
            System.out.println("Bill signed!");
            passed = true;
        }catch(Exception e){
            System.out.println("Bill was not signed because of "+e.getMessage());
        }
        return passed;
    }

    public President getPresident(){
        return this.president;
    }

}
