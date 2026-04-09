class Marker1 {
    private String name;
    private String color;
    private int price;
    private int year;

    Marker1(String name, String color, int price, int year) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// This is violating SRP - 
// class Invoice {
//     private Marker marker;
//     private int quantity;
//     private int total;

//     Invoice(Marker marker, int quantity) {
//         this.marker = marker;
//         this.quantity = quantity;
//     }

//     public void calculateTotal() {
//         this.total = this.marker.getPrice() * quantity;
//     }

//     public void saveToDb() {
//         System.out.println("Saved");
//     }

//     public void printInvoice() {
//         System.out.println("Printing invoice");
//     }
// }


// Lets break down the SRP - 
class Invoice1 {
    private Marker1 marker;
    private int quantity;
    private int total;

    public Invoice1(Marker1 marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    // Only 1 Responsibility to calculate total 
    public void calculateTotal() {
        this.total = this.marker.getPrice() * quantity;
    }

    public int getTotal() {
        return total;
    }

    public Marker1 getMarker() {
        return marker;
    }

    public int getQuantity() {
        return quantity;
    }
}

class InvoiceDao1 {
    Invoice1 invoice;

    public InvoiceDao1(Invoice1 invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        Marker1 marker = invoice.getMarker();
        int total = invoice.getTotal();
        String name = marker.getName();
        System.out.println("Saved to db" + total + name);
    }
}

class InvoicePrinter1 {
    Invoice1 invoice;

    public InvoicePrinter1(Invoice1 invoice) {
        this.invoice = invoice;
    }

    public void printInvoice() {
        Marker1 marker = invoice.getMarker();
        String name = marker.getName();
        
        System.out.println("Printing Invoice for name" + name);
    }
}

public class SingleResponsibility {
    public static void main(String[] args) {
        Invoice1 invoice = new Invoice1(new Marker1("Sm", "red", 20, 2023), 10);
        InvoiceDao1 invoiceDao1 = new InvoiceDao1(invoice);
        InvoicePrinter1 invoicePrinter = new InvoicePrinter1(invoice);

        invoice.calculateTotal();
        invoiceDao1.saveToDB();
        invoicePrinter.printInvoice();
    }
}