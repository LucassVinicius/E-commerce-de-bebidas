package DarioBebidas.DarioBebidas.Dto;

public class DrinkRequest {
    private String name;
    private Double price;
    private String imageURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getimageURL() {
        return imageURL;
    }

    public void setImageURl(String imageURL) {
        this.imageURL = imageURL;
    }
}


