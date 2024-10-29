@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(length = 30, nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeAddress;

    @Column
    private String storeImg;

    @ManyToOne
    @JoinColumn(name = "store_category_id", nullable = false)
    private StoreCategory storeCategory;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // Getters, setters, and constructors
}
