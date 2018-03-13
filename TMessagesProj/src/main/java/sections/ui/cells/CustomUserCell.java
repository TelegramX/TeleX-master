package sections.ui.cells;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;


public class CustomUserCell extends FrameLayout {


    private BackupImageView avatarImageView;
    private SimpleTextView nameTextView;
    private AvatarDrawable avatarDrawable;
    private ImageView imageView;
    private TLObject currentObject = null;
    private Drawable curDrawable = null;

    private int currentDrawable;
    private CharSequence currentName;

    private TLRPC.FileLocation lastAvatar = null;
    private String lastName = null;

    private int radius = 32;

    public CustomUserCell(Context context) {
        super(context);

        avatarDrawable = new AvatarDrawable();

        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setVisibility(GONE);
        addView(imageView, LayoutHelper.createFrame(LayoutParams.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT,(Gravity.CENTER | Gravity.TOP),5, 5,5, 5));


        avatarImageView = new BackupImageView(context);
        avatarImageView.setRoundRadius(AndroidUtilities.dp(24));
        addView(avatarImageView, LayoutHelper.createFrame(48, 48, Gravity.CENTER | Gravity.TOP, 5, 5,5, 5));

        nameTextView = new SimpleTextView(context);
        nameTextView.setTextColor(0xff212121);
        nameTextView.setTextSize(15);
        nameTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        addView(nameTextView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 20, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.BOTTOM, 3,3,3,3));


    }


    public void setData(TLRPC.TL_dialog user, CharSequence name, int resId) {
        if (user == null) {
            currentObject = null;
            nameTextView.setText("");
            avatarImageView.setImageDrawable(null);
            return;
        }
        currentName = name;
        currentObject = user;
        currentDrawable = resId;
        update(0);
    }

    public void update(int mask) {
        if (currentObject == null) {
            return;
        }
        TLRPC.FileLocation photo = null;
        String newName = null;
        TLRPC.User currentUser = null;
        TLRPC.Chat currentChat = null;
        if (currentObject instanceof TLRPC.User) {
            currentUser = (TLRPC.User) currentObject;
            if (currentUser.photo != null) {
                photo = currentUser.photo.photo_small;
            }
        } else {
            currentChat = (TLRPC.Chat) currentObject;
            if (currentChat.photo != null) {
                photo = currentChat.photo.photo_small;
            }
        }
        if (mask != 0) {
            boolean continueUpdate = false;
            if ((mask & MessagesController.UPDATE_MASK_AVATAR) != 0) {
                if (lastAvatar != null && photo == null || lastAvatar == null && photo != null && lastAvatar != null && photo != null && (lastAvatar.volume_id != photo.volume_id || lastAvatar.local_id != photo.local_id)) {
                    continueUpdate = true;
                }
            }
            if (currentUser != null && !continueUpdate && (mask & MessagesController.UPDATE_MASK_STATUS) != 0) {
                int newStatus = 0;
                if (currentUser.status != null) {
                    newStatus = currentUser.status.expires;
                }

            }
            if (!continueUpdate && currentName == null && lastName != null && (mask & MessagesController.UPDATE_MASK_NAME) != 0) {
                if (currentUser != null) {
                    newName = UserObject.getUserName(currentUser);
                } else {
                    newName = currentChat.title;
                }
                if (!newName.equals(lastName)) {
                    continueUpdate = true;
                }
            }
            if (!continueUpdate) {
                return;
            }
        }

        if (currentUser != null) {
            avatarDrawable.setInfo(currentUser);
        } else {
            avatarDrawable.setInfo(currentChat);
        }

        if (currentName != null) {
            lastName = null;
            nameTextView.setText(currentName);
        } else {
            if (currentUser != null) {
                lastName = newName == null ? UserObject.getUserName(currentUser) : newName;
            } else {
                lastName = newName == null ? currentChat.title : newName;
            }
            nameTextView.setText(lastName);
        }

        if (imageView.getVisibility() == VISIBLE && currentDrawable == 0 || imageView.getVisibility() == GONE && currentDrawable != 0) {
            imageView.setVisibility(currentDrawable == 0 ? GONE : VISIBLE);
            imageView.setImageResource(currentDrawable);
            if (currentDrawable != 0)
                imageView.setImageDrawable(getResources().getDrawable(currentDrawable));
        }
        //Tele X
        if (curDrawable != null) imageView.setImageDrawable(curDrawable);
        avatarImageView.getImageReceiver().setRoundRadius(AndroidUtilities.dp(radius));
//        avatarDrawable.setRadius(AndroidUtilities.dp(radius));
        //
        avatarImageView.setImage(photo, "50_50", avatarDrawable);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }


    public void setNameSize(int size) {
        nameTextView.setTextSize(size);
    }


    public void setImageDrawable(Drawable drawable) {
        curDrawable = drawable;
    }

    public void setAvatarRadius(int value) {
        radius = value;
    }
}
