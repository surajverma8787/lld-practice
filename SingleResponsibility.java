class Marker {
    private String name;
    private String color;
    private int price;
    private int year;

    Marker(String name, String color, int price, int year) {
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
class Invoice {
    private Marker marker;
    private int quantity;
    private int total;

    public Invoice(Marker marker, int quantity) {
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

    public Marker getMarker() {
        return marker;
    }

    public int getQuantity() {
        return quantity;
    }
}

class InvoiceDao {
    Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB() {
        Marker marker = invoice.getMarker(); 
        int total = invoice.getTotal();
        String name = marker.getName();
        System.out.println("Saved to db" + total + name);
    }
}

class InvoicePrinter {
    Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void printInvoice() {
        Marker marker = invoice.getMarker(); 
        String name = marker.getName();
        
        System.out.println("Printing Invoice for name" + name);
    }
}

public class SingleResponsibility {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(new Marker("Sm", "red", 20, 2023), 10);
        InvoiceDao invoiceDao = new InvoiceDao(invoice);
        InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);

        invoice.calculateTotal();
        invoiceDao.saveToDB();
        invoicePrinter.printInvoice();
    }
}