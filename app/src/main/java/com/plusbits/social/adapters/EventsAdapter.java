package com.plusbits.social.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.plusbits.social.R;
import com.plusbits.social.models.Event;
import com.plusbits.social.utils.DownloadImageTask;

import java.util.ArrayList;

/**
 * Created by Marc on 10/06/2015.
 */
public class EventsAdapter  extends ArrayAdapter<Event> {
    // View lookup cache
    private static class ViewHolder {
        String idEvent;
        TextView name;
        TextView desc;
        ImageView image;
    }

    public EventsAdapter(Context context, ArrayList<Event> events) {
        super(context, R.layout.item_event, events);
        Ion ion = Ion.getInstance(getContext(),"ion");
        ion.getCache().clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_event, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.tvDesc);
            viewHolder.image = (ImageView)convertView.findViewById(R.id.ivEvent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(event.getName());
        viewHolder.desc.setText(event.getDescription());
        viewHolder.idEvent = event.getId();

        //Assignem el color de la vista
        int idColor = event.isValidated() ? R.color.validated_event : R.color.not_validated_event;
        convertView.setBackgroundColor(getContext().getResources().getColor(idColor));
        Log.d("ImageUrl", event.getImageURL());
        //viewHolder.image.setImageResource(R.drawable.placeholder_image);
        //new DownloadImageTask(viewHolder.image).execute(event.getImageURL());

        Ion.with(viewHolder.image)
                .placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                //.animateLoad(spinAnimation)
                // .animateIn(fadeInAnimation)
                .load(event.getImageURL());

        // Return the completed view to render on screen
        return convertView;
    }
}
