<script>
    import AnalyticsContent from "../components/AnalyticsContent.svelte";
  
    let { open = false, investments = [], onClose } = $props();
  </script>
  
  {#if open}
    <div class="modal-overlay">
      <div class="modal large">
        <button class="close-btn" type="button" onclick={onClose}>×</button>
  
        <AnalyticsContent investments={investments} />
      </div>
    </div>
  {/if}
  
  