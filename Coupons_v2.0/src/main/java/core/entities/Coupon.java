package core.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Class describes Coupon entity
 */
@Entity
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer company_id;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer amount;
	private Double price;
	private String image;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = { @JoinColumn(name = "coupon_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
	private List<Customer> customers;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "company_id")
	private Company company;

	/** Empty constructor */
	public Coupon() {
	}

	/**
	 * Constructor creates coupon instance
	 * 
	 * @param Integer  company_id
	 * @param Category category
	 * @param String   title
	 * @param String   description
	 * @param Date     startDate
	 * @param Date     endDate
	 * @param Integer  amount
	 * @param Double   price
	 * @param String   image
	 */
	public Coupon(Integer company_id, Category category, String title, String description, Date startDate, Date endDate,
			Integer amount, Double price, String image) {
		this.company_id = company_id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * Get method returns a coupon id
	 * 
	 * @return Integer id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set method for a coupon id. You should use the method to set coupon id you
	 * get from database.
	 * 
	 * @param Integer id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get method returns a company id that a coupon belongs to
	 * 
	 * @return Integer company_id
	 */
	public Integer getCompany_id() {
		return company_id;
	}

	/**
	 * Set method for a company id that a coupon belongs to
	 * 
	 * @param Integer company_id
	 */
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	/**
	 * Get method returns a coupon category
	 * 
	 * @return Category category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set method for a coupon category
	 * 
	 * @param Category category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Get method returns a coupon title
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set method for a coupon title
	 * 
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get method returns a coupon description
	 * 
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set method for a coupon description
	 * 
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get method return a coupon start date
	 * 
	 * @return Date startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set method for a coupon start date
	 * 
	 * @param Date startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get method for a coupon end date
	 * 
	 * @return Date endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set method for a coupon end date
	 * 
	 * @param Date endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Get method returns a coupon available amount
	 * 
	 * @return Integer amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Set method for a coupon available amount
	 * 
	 * @param Integer amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * Get method returns a coupon price
	 * 
	 * @return Double price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set method for a coupon price
	 * 
	 * @param Double price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get method for a coupon image URL
	 * 
	 * @return String image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Set method for a coupon image URL
	 * 
	 * @param String image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company_id=" + company_id + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
