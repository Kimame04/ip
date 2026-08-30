package jiji.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import jiji.exception.JijiStorageException;
import jiji.task.Deadline;
import jiji.task.Event;
import jiji.task.Task;
import jiji.task.TaskList;
import jiji.task.TaskType;
import jiji.task.Todo;

/**
 * Manages loading tasks from and saving tasks to persistent disk storage.
 */
public class Storage {

    private final Path filePath;

    /**
     * Constructs a Storage instance with the given relative or absolute file path.
     *
     * @param filePath Path string to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads the list of tasks from the storage file. If the file does not exist,
     * an empty task list is returned. Corrupted or malformed lines are skipped gracefully.
     *
     * @return A list of {@link Task} objects loaded from disk.
     * @throws JijiStorageException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws JijiStorageException {
        List<Task> tasks = new ArrayList<>();
        try {
            if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                return tasks;
            }
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] parts = trimmed.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                TaskType taskType = TaskType.fromCode(type);
                if (taskType == null) {
                    continue;
                }

                Task task = null;
                switch (taskType) {
                    case TODO:
                        task = new Todo(description);
                        break;
                    case DEADLINE:
                        if (parts.length >= 4) {
                            String by = parts[3].trim();
                            task = new Deadline(description, by);
                        }
                        break;
                    case EVENT:
                        if (parts.length >= 5) {
                            String from = parts[3].trim();
                            String to = parts[4].trim();
                            task = new Event(description, from, to);
                        }
                        break;
                    default:
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new JijiStorageException("Could not load tasks from storage: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param taskList The {@link TaskList} containing tasks to persist.
     * @throws JijiStorageException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws JijiStorageException {
        try {
            if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            List<String> lines = new ArrayList<>();
            for (Task task : taskList.getAllTasks()) {
                lines.add(task.toFileFormat());
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new JijiStorageException("Could not save tasks to storage: " + e.getMessage());
        }
    }
}
