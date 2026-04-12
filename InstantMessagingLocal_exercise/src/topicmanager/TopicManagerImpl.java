package topicmanager;

import util.Subscription_check;
import util.Topic;
import util.Topic_check;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import publisher.Publisher;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

  private Map<Topic, Publisher> topicMap;

  public TopicManagerImpl() {
    topicMap = new HashMap<Topic, Publisher>();
  }

  @Override
  public Publisher addPublisherToTopic(Topic topic) {
    Publisher publisher = topicMap.get(topic);
    
    if (publisher == null) {
        publisher = new PublisherImpl(topic);
        topicMap.put(topic, publisher);
    }
    else {
        publisher.incPublishers();
    }
    return publisher;
  }

  @Override
  public void removePublisherFromTopic(Topic topic) {
    Publisher publisher = topicMap.get(topic);
    
    if (publisher != null) {
        int remaining = publisher.decPublishers();
        if (remaining == 0) {
            topicMap.remove(topic);
        }
    }
  }

  @Override
  public Topic_check isTopic(Topic topic) {
    if (topicMap.containsKey(topic)) {
        return new Topic_check(topic, true);
    }
    else { 
        return new Topic_check(topic, false);
    }
  }

  @Override
  public List<Topic> topics() {
    return new ArrayList<Topic>(topicMap.keySet());
  }

  @Override
  public Subscription_check subscribe(Topic topic, Subscriber subscriber) {
    Publisher publisher = topicMap.get(topic);
    
    if (publisher == null) {
        return new Subscription_check(topic, Subscription_check.Result.NO_TOPIC);
    }
    
    publisher.attachSubscriber(subscriber);
    return new Subscription_check(topic, Subscription_check.Result.OKAY);
  }

  @Override
  public Subscription_check unsubscribe(Topic topic, Subscriber subscriber) {
    Publisher publisher = topicMap.get(topic);
    
    if (publisher == null) {
        return new Subscription_check(topic, Subscription_check.Result.NO_TOPIC);
    }
    
    boolean removed = publisher.detachSubscriber(subscriber);
    if (removed) {
        return new Subscription_check(topic, Subscription_check.Result.OKAY);
    }
    else {
        return new Subscription_check(topic, Subscription_check.Result.NO_SUBSCRIPTION);
    }
  }

  
}
