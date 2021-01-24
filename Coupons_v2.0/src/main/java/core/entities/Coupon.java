package core.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Class describes Coupon entity
 * 
 * @autor Daniel Sorkin
 * 
 *        last update 2021-1-24
 */
@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int company_id;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "customers_vs_coupons", joinColumns = { @JoinColumn(name = "coupon_id") }, inverseJoinColumns = {
			@JoinColumn(name = "customer_id") })
	private List<Customer> customers;

	/**
	 * Constructor creates coupon instance
	 * 
	 * @param int      company_id
	 * @param Category category
	 * @param String   title
	 * @param String   description
	 * @param Date     startDate
	 * @param Date     endDate
	 * @param int      amount
	 * @param double   price
	 * @param String   image
	 */
	public Coupon(int company_id, Category category, String title, String description, Date startDate, Date endDate,
			int amount, double price, String image) {
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
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set method for a coupon id. You should use the method to set coupon id you
	 * get from database.
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get method returns a company id that a coupon belongs to
	 * 
	 * @return int company_id
	 */
	public int getCompany_id() {
		return company_id;
	}

	/**
	 * Set method for a company id that a coupon belongs to
	 * 
	 * @param int company_id
	 */
	public void setCompany_id(int company_id) {
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
	 * @return int amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Set method for a coupon available amount
	 * 
	 * @param int amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * Get method returns a coupon price
	 * 
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set method for a coupon price
	 * 
	 * @param double price
	 */
	public void setPrice(double price) {
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
