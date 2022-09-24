
# UI browser Automation Test

### Testing Website

```http
  https://www.flip.kz/
```

 Test Case | Step Action   | Step Expected               
 :-------- | :------- | :------------------------- 
 `1)login with right credentials` | 1. Navigate to the main page | Main page should appear
||2. Press the "Войти" button|The login form should appear|
||3. Input right credentials|Login should be successful|
| `2)login with wrong credentials` | 1. Navigate to the main page | Main page should appear|
||2. Press the "Войти" button|The login form should appear|
||3. Input wrong credentials|Login should fail|
| `3) Verify logout function` | 1. Hover over profile in the top-right corner |Function list should appear, which includes logout|
||2. Press the logout button|The page should refresh with no user profile|
| `4)Attempt to order a product with wrong address`| 1. Scroll down the products list and press any product |Product page should appear|






## Authors

- [@Madikhan Sadykov](https://github.com/MadikhanSadykov)
- [@Vladislav Ozhogov](https://github.com/Vemendu)
- [@Mirkhat Asen](https://github.com/orgs/DebugNuggets/people/MirkhatA)
- [@Alua Seidemet]()

