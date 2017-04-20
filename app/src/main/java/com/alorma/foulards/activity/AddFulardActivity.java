package com.alorma.foulards.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.R;
import com.alorma.foulards.data.Agrupament;
import com.alorma.foulards.data.FulardSearch;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import com.alorma.foulards.view.FulardFactory;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class AddFulardActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

  private static int REQUEST_CODE_FULARD = 1212;
  private static final int RC_SIGN_IN = 121;

  @BindView(R.id.loginButton) View loginButton;
  @BindView(R.id.userImage) ImageView userImage;
  @BindView(R.id.userName) TextView userName;

  @BindView(R.id.selectorFulard) View selectorFulard;
  @BindView(R.id.addFulard) View addFulard;
  @BindView(R.id.fulardLayout) ViewGroup fulardLayout;
  @BindDimen(R.dimen.fulard_search_size) int dimenFulard;

  private GoogleApiClient mGoogleApiClient;
  private FirebaseAuth mAuth;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_fulard);
    ButterKnife.bind(this);

    selectorFulard.setOnClickListener(v -> onSelectFulard());
    // addFulard.setOnClickListener(v -> onAddFulard(search));

    initFirebaseAuth();

    loginButton.setOnClickListener(v -> onLoginClick());
  }

  @Override
  protected void onStart() {
    super.onStart();

    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser != null) {
      updateUI(currentUser);
    } else {
      updateNoUserUI();
    }
  }

  private void onSelectFulard() {
    Intent intent = new Intent(this, FulardSearchBuilderActivity.class);
    startActivityForResult(intent, REQUEST_CODE_FULARD);
  }

  private void onSearchLoaded(FulardCustomization customization, FulardSearch search) {
    if (search != null && customization != null) {
      Fulard fulard = new FulardFactory().get(this, search.getFulardType());
      fulard.fill(customization);

      fulardLayout.removeAllViews();
      fulardLayout.addView(fulard, dimenFulard, dimenFulard);
    }
  }

  private void initFirebaseAuth() {
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build();

    mGoogleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this, this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();

    mAuth = FirebaseAuth.getInstance();
  }

  private void onLoginClick() {

    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      if (result.isSuccess()) {
        // Google Sign In was successful, authenticate with Firebase
        GoogleSignInAccount account = result.getSignInAccount();
        firebaseAuthWithGoogle(account);
      } else {
        updateNoUserUI();
      }
    } else if (resultCode == Activity.RESULT_OK) {
      if (requestCode == REQUEST_CODE_FULARD) {
        FulardCustomization customization =
            (FulardCustomization) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_CUSTOMIZATION);
        FulardSearch search = (FulardSearch) data.getExtras().getSerializable(FulardSearchBuilderActivity.Extras.EXTRA_SEARCH);
        onSearchLoaded(customization, search);
      }
    }
  }

  private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this, task -> {
          if (task.isSuccessful()) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
          } else {
            updateNoUserUI();
          }
        });
  }

  private void updateUI(FirebaseUser user) {
    loginButton.setVisibility(View.GONE);
    loginButton.setEnabled(false);

    String displayName = user.getDisplayName();
    userName.setText(displayName);

    if (user.getPhotoUrl() != null) {
      Glide.with(this).load(user.getPhotoUrl()).into(userImage);
    }

    showAddViews();
  }

  private void showAddViews() {
    selectorFulard.setVisibility(View.VISIBLE);
    selectorFulard.setEnabled(true);
    addFulard.setVisibility(View.INVISIBLE);
    addFulard.setEnabled(false);
  }

  private void updateNoUserUI() {
    loginButton.setVisibility(View.VISIBLE);
    loginButton.setEnabled(true);
    selectorFulard.setVisibility(View.INVISIBLE);
    selectorFulard.setEnabled(false);
    addFulard.setVisibility(View.INVISIBLE);
    addFulard.setEnabled(false);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  private void onAddFulard(FulardSearch search) {
    if (search != null) {
      UUID uuid = UUID.randomUUID();

      Agrupament agrupament = new Agrupament("Test", false);

      FirebaseDatabase database = FirebaseDatabase.getInstance();
      //database.getReference("agrupaments").child(uuid.toString()).setValue(agrupament);
      //database.getReference("customization").child(uuid.toString()).setValue(search);
    }
  }
}
