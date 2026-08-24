package jiji;

import jiji.command.Command;
import jiji.exception.JijiException;
import jiji.exception.JijiStorageException;
import jiji.parser.Parser;
import jiji.storage.Storage;
import jiji.task.TaskList;
import jiji.ui.Ui;

/**
 * Main entry point for the Jiji personal assistant chatbot.
 * Coordinates the Ui, Storage, TaskList, and Parser components to execute user commands.
 */
public class Jiji {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Jiji chatbot instance with the specified file path for persistent data storage.
     *
     * @param filePath The file path for data persistence (e.g. "data/jiji.txt").
     */
    public Jiji(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (JijiStorageException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        this.tasks = loadedTasks;
    }

    /**
     * Starts and executes the main command processing loop of Jiji.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && ui.hasNextCommand()) {
            String fullCommand = ui.readCommand();
            if (fullCommand.trim().isEmpty()) {
                continue;
            }
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (JijiException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Jiji("data/jiji.txt").run();
    }
}
