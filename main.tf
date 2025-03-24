provider "google" {
  project = "assignment-454616"
  region  = "us-central1"
}

resource "google_container_cluster" "primary" {
  name     = "assignment3-cluster"
  location = "us-central1-a" 
  remove_default_node_pool = true
  initial_node_count       = 1

  network    = "default"
  subnetwork = "default"
}

resource "google_container_node_pool" "primary_nodes" {
  name       = "node-pool"
  location   = google_container_cluster.primary.location
  cluster    = google_container_cluster.primary.name
  node_count = 1

  node_config {
    preemptible  = false
    machine_type = "e2-medium"
    image_type   = "COS_CONTAINERD"
    disk_size_gb = 10
    disk_type    = "pd-standard"
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform"
    ]
  }
}

resource "google_compute_disk" "persistent_disk" {
  name  = "sat_persistent_disk"
  type  = "pd-standard"
  zone  = "us-central1-a"
  size  = 1
}
