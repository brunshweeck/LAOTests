package core.manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    private final LocalDate date;
    private final List<Command> commands;

    public Facture() {
        date = LocalDate.now();
        commands = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void printFacture() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Facture: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(" CODE |        PRODUCT NAME          | PRICE | QUANTITY | TOTAL PRICE");
        long ttc = 0;
        for (Command command : commands) {
            System.err.println("-------------------------------------------------------------------------");
            System.err.println(command.toString());
            ttc += command.getTotalPrice();
        }
        System.err.println("-------------------------------------------------------------------------");
        System.out.println("                                                TOTAL SOLD: " + ttc);
    }
}
