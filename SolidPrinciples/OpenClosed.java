interface InvoiceDao {
    void save();
}

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

class DatabaseInvoice implements InvoiceDao {
    Invoice invoice;

    public DatabaseInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void save() {
        Marker marker = invoice.getMarker(); 
        int total = invoice.getTotal();
        String name = marker.getName();
        System.out.println("Saved to db" + total + name);
    }
}

class FileInvoice implements InvoiceDao {
    Invoice invoice;

    public FileInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void save() {
        Marker marker = invoice.getMarker(); 
        int total = invoice.getTotal();
        String name = marker.getName();
        System.out.println("Saved to File" + total + name);
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

public class OpenClosed {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(new Marker("Sm", "red", 20, 2023), 10);
        InvoiceDao invoiceDao = new DatabaseInvoice(invoice);
        InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);

        invoice.calculateTotal();
        invoiceDao.save();
        invoicePrinter.printInvoice();
    }
}