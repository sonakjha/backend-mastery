import java.util.*;

// 1) A generic, reusable repository contract
interface Repository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}

class User {
    private final Long id;
    private final String name;

    User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    Long getId() { return id; }
    String getName() { return name; }

    @Override
    public String toString() { return "User{" + id + ", " + name + "}"; }
}

// 2) One implementation, written once, reusable for any entity
class InMemoryUserRepository implements Repository<User, Long> {
    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}

// 3) Generic wrapper for API responses: one class, any payload type
class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;

    ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, "OK");
    }

    static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    @Override
    public String toString() {
        return "ApiResponse{success=" + success + ", data=" + data + ", message=" + message + "}";
    }
}

public class RepositoryDemo {
    public static void main(String[] args) {
        Repository<User, Long> repo = new InMemoryUserRepository();
        repo.save(new User(1L, "Sonak"));

        ApiResponse<User> found = repo.findById(1L)
                .map(ApiResponse::ok)
                .orElseGet(() -> ApiResponse.error("User not found"));

        System.out.println(found);
    }
}