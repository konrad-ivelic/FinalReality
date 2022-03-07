package com.github.konradiv.finalreality.gui;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.controller.turnPhases.EnemyTurnPhase;
import com.github.konradiv.finalreality.controller.turnPhases.InitialPhase;
import com.github.konradiv.finalreality.controller.turnPhases.PlayerTurnPhase;
import com.github.konradiv.finalreality.controller.turnPhases.WaitPhase;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/**
 * Main entry point for the application.
 *
 * This application launches a simulation of a game of Final Reality
 * when a player character has the turn, it cannot attack until it has been
 * equipped with a weapon, when this happens it can attack ONCE in their turn,
 * whenever you attack your turn is over.
 *
 * If all of the player characters die, then the player loses.
 * If all the enemy characters die, then the player wins.
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 */
public class FinalReality extends Application {
  GameController controller = new GameController();
  List<ICharacter> enemyCharacters;
  List<IPlayerCharacter> playerCharacters;
  List<IWeapon> playerInventory;

  Button initializeTurnButton = new Button("Initialize turn");
  Button attackButton = new Button("Attack");
  Button equipButton = new Button("Equip");
  Button nextTurnButton = new Button("Next turn");

  VBox game = new VBox();

  FlowPane Enemies = new FlowPane();
  FlowPane PlayerCharacters = new FlowPane();
  FlowPane UserInteraction = new FlowPane();
  FlowPane outcomePane = new FlowPane();

  VBox wait = new VBox();
  VBox characterInfo = new VBox();
  Label actualWeapon = new Label();
  Label actualCharacter = new Label();
  Label actualHealth = new Label();
  Label outcome = new Label();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    controller.createEnemy("Goblin", 100, 20, 10, 50);
    controller.createEnemy("Giant", 200, 40, 20, 30);
    controller.createEnemy("Spider",70,20,10,60);
    controller.createEnemy("Worm",50,10,5,25);
    controller.createEnemy("Vampire",150,30,15,40);

    controller.createKnight("Knight Alma", 100, 20);
    controller.createBlackMage("Black Mage Vinko", 100, 10, 100);
    controller.createEngineer("Engineer U-Cursito",100,15);
    controller.createThief("Thief Politician",100,5);
    controller.createWhiteMage("White Mage Kumba",100,100,10);

    controller.createAxe("Axe", 60, 30);
    controller.createSword("Sword", 50, 20);
    controller.createKnife("Knife",30,10);
    controller.createStaff("Staff",20,20,30);
    controller.createBow("Bow",30,10);

    enemyCharacters = controller.getEnemyCharacters();
    playerCharacters = controller.getPlayerCharacters();
    playerInventory = controller.getPlayerInventory();

    controller.startGame();
    startAnimator();
    /* PLAYER CHARACTERS FIRST THEN THE ENEMIES
     */

    primaryStage.setTitle("Final reality");

    /* We create flow pane that will show the enemies data */
    Enemies.setAlignment(Pos.TOP_CENTER);
    flowPaneCreation(Enemies);

    for (ICharacter enemy : enemyCharacters) {
      dataCreation(Enemies, enemy.getData());
    }

    /* We create the flow pane that will show the player characters data */
    PlayerCharacters.setAlignment(Pos.CENTER);
    flowPaneCreation(PlayerCharacters);

    for (IPlayerCharacter playerCharacter : playerCharacters) {
      dataCreation(PlayerCharacters, playerCharacter.getData());
    }

    wait.getChildren().add(new Label("Wait until a character has entered the queue and try again"));

    /* We create the flow pane that will hold the part that the user will interact to */
    UserInteraction.setAlignment(Pos.BASELINE_CENTER);
    flowPaneCreation(UserInteraction);

    /* We create the flow pane that will hold the outcome of the game */
    outcomePane.setAlignment(Pos.BASELINE_CENTER);
    flowPaneCreation(outcomePane);
    outcomePane.getChildren().add(outcome);


    initializeTurnButton.setOnAction(event -> {
      controller.phaseActivity();
      UserInteraction.getChildren().removeAll(initializeTurnButton,characterInfo);

      ICharacter turnHolder = controller.getTurnHolder();

      if (controller.getPhase().equals(new PlayerTurnPhase(turnHolder))){
        characterInfo.getChildren().add(actualWeapon);
        UserInteraction.getChildren().addAll(attackButton,equipButton,characterInfo);
      }
      else if (controller.getPhase().equals(new EnemyTurnPhase(turnHolder))){
        characterInfo.getChildren().remove(actualWeapon);
        UserInteraction.getChildren().addAll(nextTurnButton,characterInfo);
      }
    });

    equipButton.setOnAction(event -> {
      UserInteraction.getChildren().removeAll(attackButton,equipButton,characterInfo);
      List<Button> weaponButtons = new ArrayList<>();
      ICharacter turnHolder = controller.getTurnHolder();

      for (IWeapon weapon:playerInventory){
        Button b = new Button(controller.weaponName(weapon));
        b.setOnAction(event2 -> {
          controller.equipWeapon((IPlayerCharacter) turnHolder,weapon);
          UserInteraction.getChildren().removeAll(weaponButtons);
          UserInteraction.getChildren().removeAll(characterInfo);

          UserInteraction.getChildren().addAll(attackButton,equipButton,characterInfo);
        });
        weaponButtons.add(b);
      }
      UserInteraction.getChildren().addAll(weaponButtons);
      UserInteraction.getChildren().add(characterInfo);
    });

    attackButton.setOnAction(event -> {
      ICharacter turnHolder = controller.getTurnHolder();
      if (controller.characterWeapon((IPlayerCharacter) turnHolder)!=null) {
        UserInteraction.getChildren().removeAll(attackButton, equipButton, characterInfo);
        List<Button> enemyButtons = new ArrayList<>();
        for (ICharacter enemy : enemyCharacters) {

          Button b = new Button(controller.characterName(enemy));
          b.setOnAction(event2 -> {
            UserInteraction.getChildren().removeAll(enemyButtons);
            controller.attackCharacter(turnHolder, enemy);
            controller.phaseActivity();
            UserInteraction.getChildren().add(nextTurnButton);
            characterInfo.getChildren().remove(actualWeapon);
          });
          enemyButtons.add(b);
        }
        UserInteraction.getChildren().addAll(enemyButtons);
      }
    });


    nextTurnButton.setOnAction(event -> {
      controller.phaseActivity();
      UserInteraction.getChildren().removeAll(nextTurnButton,wait,characterInfo);
      if (controller.getPhase().equals(new InitialPhase())){
        UserInteraction.getChildren().add(initializeTurnButton);
      }
      else if (controller.emptyQueue()){
        wait.getChildren().remove(nextTurnButton);
        wait.getChildren().add(nextTurnButton);
        UserInteraction.getChildren().add(wait);
      }
      else if (controller.getPhase().equals(new WaitPhase())){
        controller.phaseActivity();
        UserInteraction.getChildren().add(nextTurnButton);
      }
      else if (controller.getPhase().equals(new PlayerTurnPhase(controller.getTurnHolder()))){
        characterInfo.getChildren().add(actualWeapon);
        UserInteraction.getChildren().addAll(attackButton,equipButton,characterInfo);
      }
      else if (controller.getPhase().equals(new EnemyTurnPhase(controller.getTurnHolder()))) {
        characterInfo.getChildren().remove(actualWeapon);
        UserInteraction.getChildren().addAll(nextTurnButton, characterInfo);
      }
    });

    characterInfo.getChildren().addAll(actualCharacter,actualHealth);
    UserInteraction.getChildren().addAll(initializeTurnButton,characterInfo);

    game.getChildren().add(Enemies);
    game.getChildren().add(PlayerCharacters);
    game.getChildren().add(UserInteraction);
    game.getChildren().add(outcomePane);

    // This sets the size of the Scene to be 1000px wide, 600px high
    Scene scene = new Scene(game, 1000, 500);
    primaryStage.setScene(scene);

    primaryStage.show();
  }

  /**
   * Sets the standard parameters of a flow pane
   * @param flowPane
   *     flow pane to be set
   */
  private void flowPaneCreation(FlowPane flowPane){
    flowPane.setPadding(new Insets(20,20,20,20));
    flowPane.setHgap(50);
    flowPane.setMinWidth(200);
    flowPane.setMaxWidth(1000);
    flowPane.setMaxHeight(200);
    flowPane.setMinHeight(200);
    flowPane.setPrefHeight(200);
  }

  /**
   * We create the data for a flow pane and a character
   * @param flowPane
   *     flow pane to add the data to
   * @param characterData
   *     data of the character to be added
   */
  private void dataCreation(FlowPane flowPane, Hashtable<String, Object> characterData) {
    flowPane.getChildren().removeAll();

    VBox data = new VBox();
    /* We put the name and health of the character first */
    data.getChildren().add(new Label(characterData.get("Name:").toString()));
    characterData.remove("Name:");
    data.getChildren().add(new Label("Health: "+characterData.get("Health:")));
    characterData.remove("Health:");

    Set<String> keySet = characterData.keySet();

    /* We add the remaining data */
    for (String key : keySet){
      Label label = new Label(key+characterData.get(key));
      data.getChildren().add(label);
    }

    /* We add the vertical box with the data to the flow pane */
    flowPane.getChildren().add(data);
  }

  /**
   * Starts animation
   */
  private void startAnimator() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int p = controller.getPlayersAlive();
        int e = controller.getEnemiesAlive();
        String characterName;
        String weapon;
        int health;
        ICharacter turnHolder = controller.getTurnHolder();
        if (turnHolder != null){
          characterName = controller.characterName(turnHolder);
          health = controller.characterHealth(turnHolder);
        } else {
          characterName = "None";
          health = 0;
        }

        /* We update the health of every player character */
        int i= 0;
        for (Node node : PlayerCharacters.getChildren()){
          ((Label) ((VBox) node).getChildren().get(1)).setText("Health: "+controller.characterHealth(playerCharacters.get(i)));
          i++;
        }
        /* We update the health of every enemy character */
        i= 0;
        for (Node node : Enemies.getChildren()){
          ((Label) ((VBox) node).getChildren().get(1)).setText("Health: "+controller.characterHealth(enemyCharacters.get(i)));
          i++;
        }

        if (controller.getPhase().equals(new PlayerTurnPhase(turnHolder))){
          IWeapon equippedWeapon = controller.characterWeapon((IPlayerCharacter) turnHolder);
          if (equippedWeapon != null){
            weapon = controller.weaponName(equippedWeapon);
          }else{
            weapon = "None";
          }
          actualWeapon.setText("Weapon held: "+ weapon);
        }
        actualCharacter.setText("Turn Holder: " + characterName);
        actualHealth.setText("Health: "+ health);
        if (p == 0 || e == 0){
          if (controller.playerWins()){
            outcome.setText("YOU WON");
          } else if (controller.playerLoses()){
            outcome.setText("GAME OVER");
          }
          game.getChildren().remove(UserInteraction);
        }

      }
    };
    timer.start();
  }
}