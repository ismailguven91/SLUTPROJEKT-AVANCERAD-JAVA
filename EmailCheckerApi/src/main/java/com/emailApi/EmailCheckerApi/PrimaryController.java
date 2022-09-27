package com.emailApi.EmailCheckerApi;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

	@FXML
	Text loggedIn;
	@FXML
	Text emailText;
	@FXML
	TextField createEmailInput;
	@FXML
	TextField createUserNameInput;
	@FXML
	TextField createPasswordInput;
	@FXML
	TextField logInUserNameInput;
	@FXML
	TextField logInPasswordInput;
	@FXML
	Text logInErrorText;

	@FXML
	Text registerError;

	EmailModel em = new EmailModel();

	App app = new App();

	UsersModel usersModel = new UsersModel();

	@FXML
	private void switchToRegisterPage() throws IOException {
		app.setRoot("CreateAccountView");
	}

	@FXML
	private void switchToLogInPage() throws IOException {
		app.setRoot("LogInView");

	}

	@FXML
	private void switchToPrimary() throws IOException {
		app.setRoot("primary");
	}

	@FXML
	private void createAccount() throws UnirestException, ParseException {
		usersModel.selectUsers();

		if (!(createEmailInput.getText().trim() == "")) {
			em.getApi(createEmailInput.getText());
			if (em.getFormat().equalsIgnoreCase("yes")) {

				if (usersModel.checkRegisterUser(createUserNameInput.getText(), createEmailInput.getText())) {
					System.out.println("User already exists");
					registerError.setText("Username taken");
				} else {
					usersModel.insertUser(createUserNameInput.getText(), createPasswordInput.getText(),
							createEmailInput.getText());
					System.out.println("User added");
				}

			} else {
				System.out.println("Wrong email format");
				registerError.setText("Wrong email format, try again");
			}
		} else {
			registerError.setText("Fill");
		}
	}

	@FXML
	private void logIn() throws IOException {
		usersModel.selectUsers();
		if (usersModel.checkLogInUser(logInUserNameInput.getText(), logInPasswordInput.getText())) {
			switchToLoggedIn();
			System.out.println("You're logged in");
		} else
			logInErrorText.setText("Wrong username or password!");
		System.out.println("You have to create an account");
	}

	@FXML
	private void switchToLoggedIn() throws IOException {
		app.setRoot("LoggedInView");

	}

	@FXML
	private void getEmailData() throws IOException, UnirestException, ParseException {

		/*
		 * emailText.setText( "E-mail: "+em.getQuery() + "\n" +
		 * "Correct format: "+em.getFormat() + "\n" + "Status: "+em.getServer_status() +
		 * "\n" + "E-mail status: "+em.getEmail_status() + "\n" +
		 * "Company e-mail: "+em.getProfessional() );
		 * 
		 */
	}
}
