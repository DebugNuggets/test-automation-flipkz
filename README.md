
# UI browser Automation Test

### Testing Website

```http
  https://www.flip.kz/
```

 Test Case | Step Action   | Step Expected               
 :-------- | :------- | :------------------------- 
 `1)login with right credentials` | 1. Navigate to the main page <br /> 2. Press the "Войти" button <br /> 3. Input right credentials | 1. Main page should appear <br /> 2. The login form should appear <br /> 3. Login should be successful
| `2)login with wrong credentials` | 1. Navigate to the main page <br />2. Press the "Войти" button <br />3. Input wrong credentials | 1. Main page should appear <br /> 2. The login form should appear<br />3. Login should fail|
| `3) Verify logout function` | 1. Hover over profile in the top-right corner <br /> 2. Press the logout button|1. Function list should appear, which includes logout <br /> 2. The page should refresh with no user profile|
| `4)Attempt to order a product with wrong address`| 1. Scroll down the products list and press any product |Product page should appear|
|`5) Being able to order product`|||
|`6) Find product by search box`|||
|`7) Apply filters to search box`|||
|`8) Use product Catalog and Category`|||
|`9) Add product to favourites`|||
|`10) Remove product from favourites`|||





## Authors

- [@Madikhan Sadykov](https://github.com/MadikhanSadykov)
- [@Vladislav Ozhogov](https://github.com/Vemendu)
- [@Mirkhat Asen](https://github.com/orgs/DebugNuggets/people/MirkhatA)
- [@Alua Seidemet]()

