package core.store;

import core.utility.Price;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeMap;

public class Bill {
    private final LocalDate date;
    private final TreeMap<Long, Command> commands;

    public Bill() {
        this.date = LocalDate.now();
        this.commands = new TreeMap<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public void addCommand(Command command) {
        Command cmd = commands.get(command.getCode());
        if (cmd == null) {
            commands.put(command.getCode(), command);
        }else {
            cmd.addQuantity(command.getQuantity());
        }
    }

    public void print() {
        String r = "-".repeat(120);
        System.err.println(r);
        System.err.println(Command.formatString("Facture: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE), 120));
        System.err.println(r);
        System.err.printf(" %s | %s | %s | %s | %s %n",
                Command.formatString("CODE", 10),
                Command.formatString("DESIGNATION", 30),
                Command.formatString("P.U", 20),
                Command.formatString("QTE", 10),
                Command.formatString("TOTAL PRICE", 20)
                );
        long ttc = 0;
        for (Command command : getCommands()) {
            System.err.println(r);
            System.err.println(command.toString());
            ttc += command.getTotalPrice().getAmount();
        }
        System.err.println(r);
        System.err.println();
        System.err.println(Command.formatString("TOTAL SOLD: " + new Price(ttc), 120));
        System.err.println();
    }
}
