@Entity
@Table(name = "store_category")
public class StoreCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeCategoryId;

    @Column(nullable = false)
    private String storeCategoryName;

    @OneToMany(mappedBy = "storeCategory", cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<>();

    // Getters, setters, and constructors
}
